package br.com.rafaeldias.apipokedex.data.local

import androidx.annotation.WorkerThread
import androidx.room.*
import br.com.rafaeldias.apipokedex.data.local.model.PokemonEntity
import br.com.rafaeldias.apipokedex.data.model.Pokemon
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import br.com.rafaeldias.apipokedex.utils.State
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun saveAllPokemon(pokemonEntity: List<PokemonEntity>?)

    @Query("SELECT * FROM pokemon_table")
     fun getAllPokemon(): List<PokemonEntity>

    @Query("UPDATE pokemon_table SET favorite=:favorite WHERE id= :id")
    suspend fun updatePokemon(id: Int,favorite : Boolean)

     @Query("DELETE FROM pokemon_table")
     suspend fun deleteAllPokemon()
}