package com.RFCore.utils;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Set;

import com.RFCore.beans.PairValues;
import com.RFCore.constants.IConstantsFields;

public class UtilsFields {

	/**
	 * Method to get all fields
	 * 
	 * @param type
	 * @return
	 */
	public static LinkedHashMap<String, Field> getAllFields(Class<?> type) {
		LinkedHashMap<String, Field> fieldsRe = new LinkedHashMap<>();
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			fieldsRe.put(field.getName(), field);
		}

		if (type.getSuperclass() != null) {
			LinkedHashMap<String, Field> fieldsSec = UtilsFields.getAllFields(type.getSuperclass());
			fieldsRe.putAll(fieldsSec);
		}

		return fieldsRe;
	}

	public static PairValues<String, Integer> getId(Object data) {
		PairValues<String, Integer> pairValues = null;
		LinkedHashMap<String, Field> fields = UtilsFields.getAllFields(data.getClass());
		Field field = fields.get(IConstantsFields.FIELD_ID);
		field.setAccessible(true);
		try {
			pairValues = new PairValues<>(IConstantsFields.FIELD_ID, (Integer) field.get(data));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pairValues;
	}

	public static void resolveAsociations(Object data) throws IllegalArgumentException, IllegalAccessException {
		if (data != null) {
			LinkedHashMap<String, Field> fields = UtilsFields.getAllFields(data.getClass());
			Set<String> keys = fields.keySet();
			Field field = null;
			Object valueField = null;
			PairValues<String, Integer> idField = null;
			for (String key : keys) {
				field = fields.get(key);
				field.setAccessible(true);
				valueField = field.get(data);
				if (valueField != null
						&& valueField.getClass().getSuperclass().getName().trim().toUpperCase().contains("BASE")) {
					idField = UtilsFields.getId(valueField);
					if (idField == null || idField.getValueB() == null) {
						field.set(data, null);
					}
				}
			}
		}
	}

}
