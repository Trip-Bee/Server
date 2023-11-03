package com.ssafy.trip.domain.spot.mapper;

import com.ssafy.trip.domain.spot.entity.Spot;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface SpotMapper {

    List<Spot> search(Map<String, String> map) throws SQLException;

}
