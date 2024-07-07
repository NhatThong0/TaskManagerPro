package com.example.taskmanagerpro.data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MyTask> __insertionAdapterOfMyTask;

  private final EntityDeletionOrUpdateAdapter<MyTask> __deletionAdapterOfMyTask;

  private final EntityDeletionOrUpdateAdapter<MyTask> __updateAdapterOfMyTask;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllTasks;

  public TaskDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMyTask = new EntityInsertionAdapter<MyTask>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Task table` (`id`,`titleTask`,`Description`,`TaskTime`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MyTask value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitleTask() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitleTask());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getTaskTime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTaskTime());
        }
      }
    };
    this.__deletionAdapterOfMyTask = new EntityDeletionOrUpdateAdapter<MyTask>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Task table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MyTask value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfMyTask = new EntityDeletionOrUpdateAdapter<MyTask>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Task table` SET `id` = ?,`titleTask` = ?,`Description` = ?,`TaskTime` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MyTask value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitleTask() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitleTask());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getTaskTime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTaskTime());
        }
        stmt.bindLong(5, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllTasks = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM `Task table`";
        return _query;
      }
    };
  }

  @Override
  public void insert(final MyTask myTask) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMyTask.insert(myTask);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final MyTask myTask) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMyTask.handle(myTask);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final MyTask myTask) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMyTask.handle(myTask);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllTasks() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllTasks.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllTasks.release(_stmt);
    }
  }

  @Override
  public LiveData<List<MyTask>> getAllTasks() {
    final String _sql = "SELECT * FROM `Task table` ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Task table"}, false, new Callable<List<MyTask>>() {
      @Override
      public List<MyTask> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleTask = CursorUtil.getColumnIndexOrThrow(_cursor, "titleTask");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "Description");
          final int _cursorIndexOfTaskTime = CursorUtil.getColumnIndexOrThrow(_cursor, "TaskTime");
          final List<MyTask> _result = new ArrayList<MyTask>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MyTask _item;
            _item = new MyTask();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpTitleTask;
            _tmpTitleTask = _cursor.getString(_cursorIndexOfTitleTask);
            _item.setTitleTask(_tmpTitleTask);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item.setDescription(_tmpDescription);
            final String _tmpTaskTime;
            _tmpTaskTime = _cursor.getString(_cursorIndexOfTaskTime);
            _item.setTaskTime(_tmpTaskTime);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
