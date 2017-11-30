package com.example.tiagomeurer.myimc2;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    ProgressBar pgrBar;
    Button calc;
    String arquivo;
    Uri outputFileUri;
    public static final String PREFS_NAME = "MyPrefsFile";
    private EditText etPeso;
    private EditText etAltura;
    private TextView etImc;

//    protected static final int TIMER_RUNTIME = 10000;
//    protected boolean nbActive;


    public void permissao(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {


            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        10);

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pgrBar = (ProgressBar) findViewById(R.id.progressBar);
        calc = (Button) findViewById(R.id.calc);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        float peso = settings.getFloat("peso",0);
        float altura = settings.getFloat("altura",0);
        float imc = settings.getFloat("imc",0);
        etPeso.setText(String.valueOf(peso));
        etAltura.setText(String.valueOf(altura));
        etImc.setText(String.valueOf("O calculo do seu imc é: "+imc));
        setarNotificacao();
        permissao();
    }

    public void setarNotificacao(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE,0);
        Intent intent = new Intent(getApplicationContext(),Notification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY,pendingIntent);
    }


    //método que calcula o IMC
    public void calcular(View view) {

        TextView tvresultado = (TextView) findViewById(R.id.tvResultado);
        EditText edtpeso = (EditText) findViewById(R.id.edtPeso);
        EditText edtaltura = (EditText) findViewById(R.id.edtAltura);

        float peso = Float.parseFloat(edtpeso.getText().toString());
        float altura = Float.parseFloat(edtaltura.getText().toString());
        float resultado = (peso / (altura * altura));
        int valor = pgrBar.getMax();
        int valorBarra = Math.round((((resultado/15)-1)*100));
        if (resultado <= 15) {
            pgrBar.setProgress(1);
            tvresultado.setText("O resultado é de "+ resultado+". Você está abaixo do peso!");
        } else if (resultado > 15 && resultado <30) {
            tvresultado.setText("O resultado é de "+ resultado+". Você está com um peso bom!");
            pgrBar.setProgress(valorBarra);
        } else  {
            tvresultado.setText("O resultado é de "+ resultado+". Você está com sobrepeso!");
            pgrBar.setProgress(100);
        }
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("peso",peso);
        editor.putFloat("altura",altura);
        editor.putFloat("imc",resultado);
        editor.commit();
    }

    public void limpar (View view){
        TextView tvresultado = (TextView) findViewById(R.id.tvResultado);
        EditText edtpeso = (EditText) findViewById(R.id.edtPeso);
        EditText edtaltura = (EditText) findViewById(R.id.edtAltura);

        edtpeso.setText("");
        edtaltura.setText("");
        tvresultado.setText("");
    }

    //metodo para encontrar a academia próximo a localização do usuário
    public void encontrarAcademia (View v){

        Uri gmmIntentUri = Uri.parse("geo:0,0?q=academia");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }


    public void capturaImagem(View view) {

        //Cria uma intenção para abrir a camera fotográfica
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Informa que a camera a ser aberta é a frontal
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);

        //Montagem do caminho onde o arquivo será salvo
        arquivo = Environment.getExternalStorageDirectory() + "/Pictures/fotoMyIMC.jpg";

        //Abre o caminho onde a foto será salva
        File file = new File(arquivo);
        outputFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        //Abre a camera
        startActivityForResult(intent, RESULT_FIRST_USER);
    }
}
