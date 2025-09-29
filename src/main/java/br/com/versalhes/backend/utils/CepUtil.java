package br.com.versalhes.backend.utils;

import java.util.*;

public class CepUtil {

    private static final Map<String, int[]> faixasCep = new HashMap<>();

    static {
        faixasCep.put("AC", new int[]{69900000, 69999999});
        faixasCep.put("AL", new int[]{57000000, 57999999});
        faixasCep.put("AM", new int[]{69000000, 69299999});
        faixasCep.put("AP", new int[]{68900000, 68999999});
        faixasCep.put("BA", new int[]{40000000, 48999999});
        faixasCep.put("CE", new int[]{60000000, 63999999});
        faixasCep.put("DF", new int[]{70000000, 73699999});
        faixasCep.put("ES", new int[]{29000000, 29999999});
        faixasCep.put("GO", new int[]{72800000, 76799999});
        faixasCep.put("MA", new int[]{65000000, 65999999});
        faixasCep.put("MG", new int[]{30000000, 39999999});
        faixasCep.put("MS", new int[]{79000000, 79999999});
        faixasCep.put("MT", new int[]{78000000, 78899999});
        faixasCep.put("PA", new int[]{66000000, 68899999});
        faixasCep.put("PB", new int[]{58000000, 58999999});
        faixasCep.put("PE", new int[]{50000000, 56999999});
        faixasCep.put("PI", new int[]{64000000, 64999999});
        faixasCep.put("PR", new int[]{80000000, 87999999});
        faixasCep.put("RJ", new int[]{20000000, 28999999});
        faixasCep.put("RN", new int[]{59000000, 59999999});
        faixasCep.put("RO", new int[]{76800000, 76999999});
        faixasCep.put("RR", new int[]{69300000, 69399999});
        faixasCep.put("RS", new int[]{90000000, 99999999});
        faixasCep.put("SC", new int[]{88000000, 89999999});
        faixasCep.put("SE", new int[]{49000000, 49999999});
        faixasCep.put("SP", new int[]{01000000, 19999999});
        faixasCep.put("TO", new int[]{77000000, 77999999});
    }

    public static String obterUf(String cep) {
        if (cep == null || cep.isEmpty()) { throw new NoSuchElementException(); }

        String cepNumerico = cep.replaceAll("\\D", "");

        int cepInt;

        try {
            cepInt = Integer.parseInt(cepNumerico);
        } catch (NumberFormatException e) {
            throw new NoSuchElementException();
        }

        for (Map.Entry<String, int[]> entry : faixasCep.entrySet()) {
            int[] faixa = entry.getValue();
            if (cepInt >= faixa[0] && cepInt <= faixa[1]) {
                return entry.getKey();
            }
        }

        throw new NoSuchElementException();
    }
}
