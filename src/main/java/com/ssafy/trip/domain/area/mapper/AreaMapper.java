package com.ssafy.trip.domain.area.mapper;

import com.ssafy.trip.domain.area.entity.Gugun;
import com.ssafy.trip.domain.area.entity.Sido;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AreaMapper {

    List<Sido> findAllSido() throws SQLException;
    List<Gugun> findAllGugunBySidoCode(int sidoCode) throws SQLException;

}
