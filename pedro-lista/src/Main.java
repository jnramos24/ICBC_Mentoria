import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> lista = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        String INSERT = "Insert";
        String DELETE = "Delete";

        System.out.println("cant de datos");
        int n = in.nextInt();
        in.nextLine();

        //ingreso de datos
        for(int i=0 ; i<n ; i++){

            System.out.println("Dato a ingresar");
            int dato = in.nextInt();
            lista.add(dato);
        }

        //Insert o delete
        //nro de operaciones
        System.out.println("Cant de operaciones");
        int q = in.nextInt();
        in.nextLine();

        for(int i=0 ; i<q ; i++) {
            //tipo de operacion
            System.out.println("Tipo de operacion: Insert o Delete");
            String s = in.nextLine();

            if(INSERT.equals(s)) {
                System.out.println("Indice: ");
                int index = in.nextInt();
                System.out.println("Numero a agregar: ");
                int numero = in.nextInt();
                in.nextLine();

                //agregar a la lista lo ingresado por el usuario
                lista.add(index, numero);
                System.out.println("Lista actualizada: " + lista); //arreglar mostrar solo valores

            } else if(DELETE.equals(s)) {
                System.out.println("Indice a borrar: ");
                int index = in.nextInt();

                //borro el indice ingresado
                lista.remove(index);
                in.nextLine();

            } else {
                System.out.println("valor invalido");
                i--;
            }
        }
        in.close();

        for(int i=0 ; i<lista.size() ; i++){
            System.out.print(lista.get(i) + " ");
        }
    }
}
