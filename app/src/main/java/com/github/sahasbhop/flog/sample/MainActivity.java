package com.github.sahasbhop.flog.sample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.sahasbhop.flog.BuildConfig;
import com.github.sahasbhop.flog.FLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FLog.setEnableLogCat(BuildConfig.DEBUG);

        File logDirectory = getLogDirectory();
        String logFileName = "sample-log.txt";

        if (logDirectory != null) {
            // Reset the log content
            File f = new File(logDirectory, logFileName);
            if (f.exists()) f.delete();

            FLog.setEnableFileLog(true, logDirectory.getAbsolutePath(), logFileName);
        }

        FLog.v("ENTER");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FLog.d("saveInstanceState: %s", savedInstanceState);
        FLog.v("EXIT");

        showLogFileContent(logDirectory, logFileName);
    }

    private void showLogFileContent(File logDirectory, String logFileName) {
        if (logDirectory != null) {
            File logFile = new File(logDirectory, logFileName);
            FLog.d("logFile exists: %s (length=%d)", logFile.exists(), logFile.length());

            if (logFile.exists()) {
                StringBuilder builder = new StringBuilder();

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(logFile));

                    String line;

                    while ((line = reader.readLine()) != null) {
                        builder.append(line).append("\n");
                    }
                } catch (Exception e) {
                    FLog.w("Error: %s", e.toString());
                }

                String tag = this.getClass().getSimpleName();
                Log.i(tag, "==============");
                Log.i(tag, builder.toString());
                Log.i(tag, "==============");
            }
        }
    }

    @TargetApi(8)
    private File getLogDirectory() {
        File logDirectory;

        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            logDirectory = Build.VERSION.SDK_INT > 8 ?
                    getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                    : Environment.getExternalStorageDirectory();
        } else {
            logDirectory = Build.VERSION.SDK_INT > 8 ? getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                    : Environment.getDataDirectory();
        }

        if (logDirectory != null && !logDirectory.exists()) {
            logDirectory.mkdirs();
        }

        FLog.d("logDirectory: %s", logDirectory.getAbsolutePath());
        return logDirectory;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
