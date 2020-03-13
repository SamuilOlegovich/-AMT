package AMT;


import AMT.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode.toLowerCase();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int total = 0;
        for (Map.Entry<Integer,Integer> entry: denominations.entrySet()) {
            total += entry.getKey() * entry.getValue();
        }
        return total;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

        // Списать деньги со счета
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        HashMap<Integer, Integer> temp = new HashMap<>();
        ArrayList<Integer> nominals = new ArrayList<>();
        temp.putAll(denominations);
        int sum = expectedAmount;

        for (Map.Entry<Integer, Integer> pair : temp.entrySet()) {
            nominals.add(pair.getKey());
        }

        Collections.sort(nominals);
        Collections.reverse(nominals);

        TreeMap<Integer, Integer> result = new TreeMap<>(
                new Comparator<Integer>()
                {
                    @Override
                    public int compare(Integer o1, Integer o2)
                    {
                        return o2.compareTo(o1);
                    }
                });

        for (Integer nominal : nominals) {
            int key = nominal;
            int value = temp.get(key);

            while (true) {
                if (sum < key || value <= 0) {
                    temp.put(key, value);
                    break;
                }
                sum -= key;
                value--;

                if (result.containsKey(key)) {
                    result.put(key, result.get(key) + 1);
                } else {
                    result.put(key, 1);
                }
            }
        }

        if (sum > 0) {
            throw new NotEnoughMoneyException();
        } else {
            denominations.clear();
            denominations.putAll(temp);
        }
        return result;
    }
}
