package com.example.mathchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView operacion;
    private TextView temporizador;
    private TextView puntaje;
    private EditText respuesta;
    private Button botonResponder;
    private Button botonDeNuevo;
    private Pregunta p;
    private int puntos = 0;
    private boolean comprobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operacion = findViewById(R.id.operacion);
        temporizador = findViewById(R.id.temporizador);
        puntaje = findViewById(R.id.puntaje);
        respuesta = findViewById(R.id.respuesta);
        botonResponder = findViewById(R.id.botonResponder);
        botonDeNuevo = findViewById(R.id.botonDeNuevo);

        p = new Pregunta();
        operacion.setText(p.getPregunta());
        tiempo();

        botonResponder.setOnClickListener(
                v -> {
                       responder();
                }
                );

        botonDeNuevo.setOnClickListener(
                v -> {

                 tiempo();
                 puntos=0;
                 botonDeNuevo.setVisibility(View.GONE);
                 mostrarNuevaPregunta();
                 botonResponder.setEnabled(true);
                }
        );

        operacion.setOnTouchListener((v, event) -> {

            switch(event.getAction()){

                case MotionEvent.ACTION_DOWN:
                    comprobar = true;
                    new Thread(() -> {

                    if(comprobar == true){

                        for(int i=0;i<20;i++){
                            try {
                                Thread.sleep(75);
                                if(comprobar==false){
                                    return;
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        runOnUiThread(
                                () -> {
                                    mostrarNuevaPregunta();
                                }
                        );
                    }
                    }
                    ).start();
                    break;

                case MotionEvent.ACTION_UP:
                    comprobar=false;
                    break;
            }

                    return true;
                }
                );





    }

    public void responder() {

        String res = respuesta.getText().toString();
        int respuestaInt = Integer.parseInt(res);
        int resCorrecta = p.getRespuesta();


        if (respuestaInt == resCorrecta) {
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
            puntos = puntos + 5;
            puntaje.setText("Puntaje: "+puntos);
            mostrarNuevaPregunta();

        }else{
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
            puntos = puntos - 4;
            puntaje.setText("Puntaje: "+puntos);
           // botonDeNuevo.setVisibility(View.VISIBLE);
           // botonResponder.setEnabled(false);
        }

        }

     public void mostrarNuevaPregunta() {
        p = new Pregunta();
        operacion.setText(p.getPregunta());
    }

    public void tiempo(){

        new Thread(
                () -> {
                    for(int i=30; i>0; i--){
                        try {
                            Thread.sleep(1000);
                            int finalI = i;
                            runOnUiThread(
                                    () -> {
                                        temporizador.setText(""+ finalI);
                                    }
                            );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                  runOnUiThread(
                          () -> {
                              botonDeNuevo.setVisibility(View.VISIBLE);
                              botonResponder.setEnabled(false);
                          }
                  );
                }

        ).start();

    }

}