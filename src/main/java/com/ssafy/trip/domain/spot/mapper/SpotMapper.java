package com.ssafy.trip.domain.spot.mapper;

import com.ssafy.trip.domain.spot.entity.Spot;
import com.ssafy.trip.domain.spot.entity.SpotType;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface SpotMapper {

    List<SpotType> findAllSpotType() throws SQLException;
    List<Spot> search(Map<String, String> map) throws SQLException;
    int countBySearch(Map<String, String> map) throws SQLException;
    Spot findBySpotId(int spotId) throws SQLException;
    void updateLikeCount(Map<String, Integer> spotMap) throws SQLException;
    List<Spot> findTopByLikeCount() throws SQLException;
}
