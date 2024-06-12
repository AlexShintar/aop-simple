package ru.shintar.aop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.shintar.aop.model.TrackTimeData;

import java.util.List;

@Repository
public interface TrackTimeDataRepository extends JpaRepository<TrackTimeData, Long>, JpaSpecificationExecutor<TrackTimeData> {
    List<TrackTimeData> findAllByMethodNameIgnoreCase(String methodName);
}
