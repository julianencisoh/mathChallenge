package com.example.mathchallenge;

public class Pregunta {

//Atributos

    private int A;
    private int B;
    private String operador;
    private String[] operandos = {"+","-","*","/"};

//Constructor

   public Pregunta(){

       this.A=(int)(Math.random()*11);
       this.B=(int)(Math.random()*11);
       int operadorRandom= (int)(Math.random()*4);
       this.operador = operandos[operadorRandom];

   }




//Metodo

   public String getPregunta(){
   return A+" "+operador+" "+B;
   }

   public int getRespuesta(){
       int respuestas = 0;

       switch (operador){

           case "+":
               respuestas = A+B;
               break;
           case "-":
               respuestas = A-B;
               break;
           case "*":
               respuestas = A*B;
               break;
           case "/":
               respuestas = A/B;
               break;

       }
       return respuestas;
   }

}
