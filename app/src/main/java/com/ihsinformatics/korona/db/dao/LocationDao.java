package com.ihsinformatics.korona.db.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.model.results.AttributeResult;
import com.ihsinformatics.korona.model.results.AttributeType;

import java.util.List;

@Dao
public interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveLocation(Location location);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAllLocation(List<Location> locations);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAttributes(List<AttributeResult> attributes);

    @Query("Select * from location where shortName = :name ")
    Location getLocationByShortName(String name);

    @Query("Select * from location")
    List<Location> getAllLocation();

    /*@Query("Select * from location where category = :category ")
    List<Location> getLocationByCategory(Integer category);*/

    @Query("Select * from attributes where contextId = :locationId ")
    List<AttributeResult> getAttributesByLocation(Integer locationId);

    @Query("Select * from location where locationName = :name ")
    Location getLocationByName(String name);

    @Query("Select * from location where categoryUuid = :categoryUUID ")
    List<Location> getLocationByCategory(String categoryUUID);

    @Query("Select * from location where parentLocationId = :parentLocationId ")
    List<Location> getLocationByParentId(Integer parentLocationId);

    /*@Query("Select * from attributes where contextId = :locationId and attributeType = :attributeType")
    AttributeResult getLocationAttribute(Integer locationId, AttributeType attributeType);*/

}
