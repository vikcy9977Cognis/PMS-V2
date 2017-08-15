package com.rclgroup.dolphin.web.dao.pms.mapper;

import com.rclgroup.dolphin.web.model.misc.CrmDropDownItemMod;
import com.rclgroup.dolphin.web.util.RutString;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ValueDescriptionRowMapper implements RowMapper {
    private String valueField;
    private String descriptionField;
    
    public ValueDescriptionRowMapper(String valueField, String descriptionField) {
        this.valueField = valueField;
        this.descriptionField = descriptionField;
    }
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            CrmDropDownItemMod item = new CrmDropDownItemMod();
            item.setValue((RutString.nullToStr(rs.getString(this.valueField))));
            item.setDescription((RutString.nullToStr(rs.getString(this.descriptionField))));
            return item;
        }
}
