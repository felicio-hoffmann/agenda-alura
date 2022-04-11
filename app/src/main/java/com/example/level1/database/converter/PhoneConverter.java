package com.example.level1.database.converter;

import androidx.room.TypeConverter;

import com.example.level1.model.PhoneType;

public class PhoneConverter {
    @TypeConverter
    public String toString(PhoneType type){
        return type.name();
    }

    @TypeConverter
    public PhoneType toPhoneType(String val){
        if (val != null){
            return PhoneType.valueOf(val);
        }
        return PhoneType.HOUSE;
    }
}
