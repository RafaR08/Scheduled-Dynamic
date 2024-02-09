package com.mbcoder.scheduler.utils;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class Split {

    public  <T> List<List<T>> splitByTime(List<T> list, long segundos) {

        int size = list.size();
        int subdivisiones = (int) Math.ceil((double) size / segundos);

        List<List<T>> result = new ArrayList<>();//para almacenar las sublistas

        for (int i = 0; i < segundos; i++) {//para dividir la lista en sublistas en los segundos que se tienen
            int endIndex = Math.min( subdivisiones, size);//se tomara el  valor de la lista ,Math.min para que no se pase del tamaño de la lista original
            List<T> sublist = list.subList(0, endIndex);//se crea una sublista desde el indice 0 hasta endIndex con el metodo sublist
            //contiene los elementos que se distibuyen durante el intervalo del tiempo
            //  System.out.println("endIndex " + endIndex+" " +sublist );
            result.add(sublist);//se agrega a la lista que contendra las sublistas

            size -= endIndex;//va restando conforme pasan los segundos (se actualiza el tamaño)
        }


        return result;
    }



    public  <T> List<List<T>> splitByTime2(List<T> list, long totalSeconds) {//toma una lista
        int size = list.size();//tamaño de la lista
        int operacionesPorSecond = (int) Math.ceil((double) size / totalSeconds);//calculo de registro por segundo.Math.ceil para redondear hacia arriba


        int[] tamañoLista = { size };  // para almacenar las sublistas
        //List<List<T>> tamañoLista = new ArrayList<>();

        return IntStream.range(0, (int) totalSeconds)//rango
                .mapToObj(segundo -> {
                    int startIndex = segundo * operacionesPorSecond;//detereminar el indice de inicio
                    int endIndex = Math.min((segundo + 1) * operacionesPorSecond, size);//determinar el indice final
                    List<T> sublist = list.subList(startIndex, endIndex);//se crea una sublista desde el indice 0 hasta endIndex con el metodo sublist
                    //contiene los elementos que se distibuyen durante el intervalo del tiempo
                    tamañoLista[0] -= sublist.size();//se reduce el tamaño restante de la lista,va restando conforme pasan los segundos (se actualiza el tamaño)
                    return sublist;
                })
                .collect(Collectors.toList());
    }
}
