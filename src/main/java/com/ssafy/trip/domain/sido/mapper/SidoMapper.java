package com.ssafy.trip.domain.sido.mapper;

import com.ssafy.trip.domain.sido.entity.Sido;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface SidoMapper {

    List<Sido> findAll() throws SQLException;

}
