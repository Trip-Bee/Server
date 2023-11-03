package com.ssafy.trip.domain.spot_type.mapper;

import com.ssafy.trip.domain.spot_type.entity.SpotType;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface SpotTypeMapper {

    List<SpotType> findAll() throws SQLException;

}
