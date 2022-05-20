package com.idnp.lab04

import android.content.Context
import android.provider.ContactsContract
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow

class DataStoreManager(private val context: Context) {
    suspend fun setDataForm(data: DataForm){
        context.dataStore.edit{preferences ->
            preferences[LBL_ACADEMICO] = data.lbl_academico
            preferences[TXT_ACADEMICO] = data.txt_academico
            preferences[LBL_ESCUELA] = data.lbl_escuela
            preferences[TXT_ESCUELA] = data.txt_escuela
            preferences[LBL_COD_ASIGNATURA] = data.lbl_cod_asignatura
            preferences[TXT_COD_ASIGNATURA] = data.txt_cod_asignatura
            preferences[LBL_NOM_ASIGNATURA] = data.lbl_nom_asignatura
            preferences[TXT_NOM_ASIGNATURA] = data.txt_nom_asignatura
            preferences[LBL_SEMESTRE] = data.lbl_semestre
            preferences[TXT_SEMESTRE] = data.txt_semestre
            preferences[LBL_DURACION] = data.lbl_duracion
            preferences[TXT_DURACION] = data.txt_duracion
            preferences[FONT_SIZE] = data.fontSize
            preferences[FONT_STYLE] = data.fontStyle
            preferences[BACKGROUND_COLOR] = data.backgroundColor
        }
    }

    fun getForm(): Flow<DataForm> = context.dataStore.data.map{ preferences ->
        DataForm(
            preferences[LBL_ACADEMICO] ?:"",
            preferences[TXT_ACADEMICO] ?:"",
            preferences[LBL_ESCUELA] ?:"",
            preferences[TXT_ESCUELA] ?:"",
            preferences[LBL_COD_ASIGNATURA] ?:"",
            preferences[TXT_COD_ASIGNATURA] ?:0,
            preferences[LBL_NOM_ASIGNATURA] ?:"",
            preferences[TXT_NOM_ASIGNATURA] ?:"",
            preferences[LBL_SEMESTRE] ?:"",
            preferences[TXT_SEMESTRE] ?:"",
            preferences[LBL_DURACION] ?:"",
            preferences[TXT_DURACION] ?:"",
            preferences[FONT_SIZE] ?:15,
            preferences[FONT_STYLE] ?:"",
            preferences[BACKGROUND_COLOR] ?:"",
        )

    }

    companion object{
        private const val DATASTORE_NAME = "form_preference"
        private val LBL_ACADEMICO = stringPreferencesKey("lbl_academico");
        private val TXT_ACADEMICO = stringPreferencesKey("txt_academico");
        private val LBL_ESCUELA = stringPreferencesKey("lbl_escuela");
        private val TXT_ESCUELA = stringPreferencesKey("txt_escuela");
        private val LBL_COD_ASIGNATURA = stringPreferencesKey("lbl_cod_asignatura");
        private val TXT_COD_ASIGNATURA = intPreferencesKey("txt_cod_asignatura");
        private val LBL_NOM_ASIGNATURA = stringPreferencesKey("lbl_nom_asignatura");
        private val TXT_NOM_ASIGNATURA = stringPreferencesKey("txt_nom_asignatura");
        private val LBL_SEMESTRE = stringPreferencesKey("lbl_semestre");
        private val TXT_SEMESTRE = stringPreferencesKey("txt_semestre");
        private val LBL_DURACION = stringPreferencesKey("lbl_duracion");
        private val TXT_DURACION = stringPreferencesKey("txt_duracion");
        private val FONT_SIZE = intPreferencesKey("font_size");
        private val FONT_STYLE = stringPreferencesKey("font_style");
        private val BACKGROUND_COLOR = stringPreferencesKey("background_color");

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}