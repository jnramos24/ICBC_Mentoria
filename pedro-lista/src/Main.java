import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> lista = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        //n = numero de datos a ingresar
        System.out.println("Cantidad de datos a ingresar: ");
        int n = in.nextInt();

        //ingreso de datos
        for(int i=0 ; i<n ; i++){
            int dato = in.nextInt();
            lista.add(dato);
        }
        System.out.println("Lista original: " + lista);
        in.nextLine();

        //Insert o delete
        System.out.println("(i)Insert or (d)Delete?");
        String s = in.nextLine();

        if(s.equals("i")) {
            System.out.println("Ingrese indice: ");
            int index = in.nextInt();

            System.out.println("Ingrese numero a agregar: ");
            int numero = in.nextInt();

            //agregar a la lista lo ingresado por el usuario
            lista.add(index, numero);
            System.out.println(lista);

        } else if (s.equals("d")) {
            System.out.println("Ingrese indice a borrar: ");
            int index = in.nextInt();

            //borro el indice ingresado
            lista.remove(index);
            System.out.println("Lista despues de eliminar: " + lista);
            in.nextLine();
        } else {
            System.out.println("valor invalido");
        }

        System.out.println("Lista final" + lista);
    }
}