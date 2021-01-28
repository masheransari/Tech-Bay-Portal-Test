package com.dotinfiny.banglesystem.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dotinfiny.banglesystem.model.api.PostModel
import com.dotinfiny.banglesystem.model.local.DbPostModel

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfPost(list: List<PostModel>)

    @Query("DELETE FROM PostModel")
    suspend fun deleteTableEnteries()

    @Query("SELECT * FROM PostModel")
    fun getPost(): LiveData<List<PostModel>>

    @Query("SELECT * FROM DbPostModel")
    fun getDbPost(): LiveData<List<DbPostModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(data: DbPostModel)

    @Query("DELETE FROM DbPostModel WHERE id=:id")
    suspend fun deleteFromFavourite(id: Int)


}
