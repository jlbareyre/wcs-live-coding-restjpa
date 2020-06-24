package com.wcs.live.jparest.dao;

import com.wcs.live.jparest.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseDao<T extends BaseModel> extends JpaRepository<T, UUID> {
}
