package com.ssafy.trip.domain.gugun.mapper;

import com.ssafy.trip.domain.gugun.entity.Gugun;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface GugunMapper {

    List<Gugun> findAllBySidoCode(int sidoCode) throws SQLException;

}
