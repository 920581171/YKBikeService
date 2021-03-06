package com.yk.bike.mapper;

import com.yk.bike.pojo.SiteLocation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SiteLocationMapper extends BaseMapper<SiteLocation> {
    String COLUMN_SITE_ID = "site_id";
    String COLUMN_LATITUDE = "latitude";
    String COLUMN_LONGITUDE = "longitude";
    String COLUMN_RADIUS = "radius";
}
