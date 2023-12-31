package br.ucs.poo.cinema.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import br.ucs.poo.cinema.cinema.Cinema;
import br.ucs.poo.cinema.cinema.Horario;
import br.ucs.poo.cinema.cinema.Sala;
import br.ucs.poo.cinema.filme.Filme;

public class HelpCartaz {
    Help h = new Help();
    HelpFilme hf = new HelpFilme();
    HelpSala hs = new HelpSala();
    HelpHorario hh = new HelpHorario();

    public int searchCartaz(Cinema cine, Scanner in, String nome) {
        Map<Integer, Filme> filmes = new TreeMap<Integer, Filme>();
        int retorno = -1;
        for (int index = 0; index < cine.getFilmeCartaz().size(); index++) {
            if (cine.getFilmeCartaz(index).getNome().equals(nome)) {
                filmes.put(index, cine.getFilmeCartaz(index));
                retorno = index;
            }
        }
        if (filmes.isEmpty()) {
            return -1;
        } else if (filmes.size() == 1) {
            return retorno;
        } else {
            System.out.println("Escolha o filme:");
            for (int index = 0; index < filmes.size(); index++) {
                System.out.println(String.format("%d - %s, %d", index + 1, filmes.get(index).getNome(),
                        filmes.get(index).getAno()));
            }
            int option = h.returnInt(in, "", 1, filmes.size());

            List<Integer> keys = new ArrayList<Integer>(filmes.keySet());
            return keys.get(option);
        }
    }

    @Deprecated
    public void addCartaz(Scanner in, Cinema cine, String nome) {
        if (nome == null) {
            nome = h.returnString(in, "Digite o nome do filme:");
        }
        int search = searchCartaz(cine, in, nome);

        // -1 = não está na lista
        if (search == -1) {
            search = hf.searchFilme(in, cine, nome);
            if (search != -1) {
                Sala sala = hs.addSala(in, cine);
                System.out.println(sala.getHorarios());
                Horario hora = hh.addHorario(in, cine, cine.getFilme(search).getTimeMin(),
                        cine.getFilme(search).getHorarios(), sala);
                saveFilme(cine, cine.getFilme(search), sala, hora);
                System.out.println("Filme adicionado ao cartaz");
            } else {
                System.out.println("Filme não encontrado. Cadastre o filme primeiro");
            }
        } else {
            System.out.println("Esse filme já está em exibição:");
            System.out.println(String.format("%s \n%s", cine.getFilme(search), cine.getFilme(search).getSala()));
            for (int i = 0; i < cine.getFilme(search).getHorarios().size(); i++) {
                System.out.println(String.format("%s", cine.getFilme(search).getHorarios(i)));
            }
            int option = h.returnInt(in, "1 - Alterar sala \n2 - Alterar horarios \nDigite 0 para cancelar", 0, 2);
            int sala = -1;
            Horario hora = null;
            if (option == 1) {
                sala = h.returnInt(in, "Digite o número da sala:", 1, cine.getSalas().size());
                cine.getFilme(search).setSala(cine.getSala(sala - 1));
            } else if (option == 2) {
                System.out.println("Escolha o horário que deseja alterar:");
                for (int i = 0; i < cine.getFilme(search).getHorarios().size(); i++) {
                    System.out.println(String.format("%s", cine.getFilme(search).getHorarios(i)));
                }
                hora = hh.addHorario(in, cine, cine.getFilme(search).getTimeMin(),
                        cine.getFilme(search).getHorarios(),cine.getSala(sala -1));
                cine.getFilme(search).setHora(hora);

            }
            saveFilme(cine, cine.getFilme(search), cine.getSala(sala - 1), hora);
        }
    }

    public void removeCartaz(Scanner in, Cinema cine) {
        int search = searchCartaz(cine, in, h.returnString(in, "Digite o nome do filme: "));

        char sn = h.returnChar(in, "Tem certeza que deseja remover o filme do cartaz(S/N): \n" + cine.getFilme(search));
        if (sn == 'S') {
            cine.removeFilme(search);
        }
    }

    public void saveFilme(Cinema cine, Filme filme, Sala sala, Horario hora) {
        cine.setFilmeCartaz(filme, sala, hora);
        writeCartaz(cine.getFilmes());
    }

    public void writeCartaz(List<Filme> list) {
        File myFile = new File("files/cartaz.dat");
        try {
            FileOutputStream myOutput = new FileOutputStream(myFile);
            ObjectOutputStream myObj = new ObjectOutputStream(myOutput);

            myObj.writeObject(list);

            myObj.close();
            myOutput.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo cartaz");
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Filme> readCartaz() {
        List<Filme> list = new ArrayList<Filme>();
        File myFile = new File("files/cartaz.dat");

        try {
            FileInputStream myInput = new FileInputStream(myFile);
            ObjectInputStream myObj = new ObjectInputStream(myInput);

            Object obj = myObj.readObject();
            list = (List<Filme>) obj;

            myObj.close();
            myInput.close();

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo cartaz");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Ocorreu um erro de classe ao ler o arquivo cartaz");
            System.out.println(e);
        }
        return list;
    }

}