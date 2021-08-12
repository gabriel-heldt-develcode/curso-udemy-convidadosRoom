package com.example.convidados.repositorio;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.convidados.model.ModeloConvidados;

@Database(entities = {ModeloConvidados.class}, version = 1)
public abstract class ConvidadoDatabase extends RoomDatabase {

    public static ConvidadoDatabase INSTANCE;
    public abstract ConvidadosDAO convidadosDAO();

    public static ConvidadoDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, ConvidadoDatabase.class, "convidados")
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);

                        }
                    })
                    .addMigrations()
                    .build();
        }
        return INSTANCE;
    }

    private static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
        }
    };
}
