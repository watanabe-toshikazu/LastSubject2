package jp.co.comnic.javalesson.webapp.lastsubject.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;

import jp.co.comnic.javalesson.webapp.lastsubject.dao.DaoException;
import jp.co.comnic.javalesson.webapp.lastsubject.dao.ScheduleDao;
import jp.co.comnic.javalesson.webapp.lastsubject.model.Schedule;




public class ControllerUtils {

	/**
	 * <p>渡されたサーブレット・パスからエンティティの完全修飾クラス名を生成して返す。</p>
	 * <p>
	 * ここで渡されるサーブレット・パスは「/employees/...」のようなものを想定し、この場合、エンティティ・クラス
	 * の完全修飾名は「jp.co.comnic.javalesson.webapp.ems.entity.employee」となる。
	 * したがって、サーブレット・パスとエンティティ・クラスとの対応ルールやパッケージ名を変更する場合にはこのメソッドは機能しない
	 * 点に注意。
	 * </p>
	 * 
	 * @param servletPath リクエストURLに含まれるサーブレット・パス 
	 * @return エンティティ・クラスの完全修飾クラス名
	 */
	public static String getFullyQualifiedClassName(String servletPath) {
		
		String className = servletPath.substring(1, servletPath.lastIndexOf('/'));
		String packageName = "jp.co.comnic.javalesson.webapp.lastsubject.model.";
		//classNameの0番目の文字列表現を返す。
		String firstCharacter = String.valueOf(className.charAt(0));
		//firstCharacterを大文字に変換する。
		firstCharacter = firstCharacter.toUpperCase();
		//classNameの任意の文字（この場合先頭）をfirstCharacterに置き換えてpackageNameに足す。
		return packageName + className.replaceFirst(".", firstCharacter);
	}

	/**
	 * <p>リクエスト・パラメーターの値からエンティティ・オブジェクトを生成して返す。</p>
	 * 
	 * @param request 
	 * @param entity
	 */
	public static void populateEntity(HttpServletRequest request, Object entity) {

		// リクエスト・パラメーターの集合をMapオブジェクトとして取得
		Map<String, String[]> parameterMap = request.getParameterMap();
		
		// Map<String, String>に変換
		Map<String, String> propertyMap = new HashMap<>();
		for (String key : parameterMap.keySet()) {
			String value = parameterMap.get(key)[0];
			if (value.contains(" ")) {
				
				value = value.replace(" ", "T");
				value= value.substring(0, value.length()-5);
				
			}
			System.out.println(value);
			propertyMap.put(key, value);
		}

		try {

			// 日付形式への対応
			DateConverter dateConverter = new DateConverter();
			dateConverter.setPattern("yyyy-MM-dd'T'HH:mm");
			
			// コンバーターの登録
			ConvertUtils.register(dateConverter,  Date.class);
			ConvertUtils.register(new ScheduleConverter(), Schedule.class);
			
			// Apache Commons ProjectのBeanUtilsを使用して
			// Mapオブジェクトからエンティティ・オブジェクトへ値をセット
			BeanUtils.populate(entity, propertyMap);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * リクエスト・パラメーターとして送られてきたString型のdept_idからDepartmentオブジェクト
	 * に変換するBeanUtils用カスタム・コンバーター
	 */
	private static class ScheduleConverter implements Converter {

		@Override
		public <T> T convert(Class<T> scheduleClass, Object value) {
			
			T schedule = null;	
			
			try {
				
				schedule = scheduleClass.cast(
						new ScheduleDao().findById(Integer.parseInt((String)value)));
				
			} catch (NumberFormatException | DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return schedule;
		}
	}
	
	/**
	 * <p>データベースに関連するエラー・メッセージから最も重要な短いメッセージを取り出して返す。</p>
	 * 
	 * @param e 例外オブジェクト
	 * @return 例外オブジェクトから取り出した簡略なメッセージ
	 */
	public static String getShortMessage(Throwable e) {

		String errorMessage = e.getCause().getMessage().split(":")[3];
		
		if (errorMessage.endsWith("Error Code")) {
			errorMessage = errorMessage.replaceAll("Error Code", "");
		}
		
		return errorMessage;
	}
}
