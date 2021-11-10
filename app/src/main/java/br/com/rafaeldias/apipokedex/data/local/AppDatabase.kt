package br.com.rafaeldias.apipokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.rafaeldias.apipokedex.data.local.model.PokemonEntity


@Database(entities = [PokemonEntity::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract val pokeDAO: PokeDAO

}