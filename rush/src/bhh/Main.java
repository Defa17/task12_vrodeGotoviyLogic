package bhh;

import java.util.HashMap;
import java.util.Map;

class Main {
    Map<Character, Character> map = new HashMap<>();
    public boolean isValid(String s) {

        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');

        int topValue = -1; // никаких скобок нет вообще или уже нет
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.containsValue(c)) {
                topValue = i; // обновление до текущего индекса

            } else {
                if (topValue == -1 || map.get(c) != s.charAt(topValue)) { // нужно узнать, какой символ содержит topValue
                    return false;
                } else {
                    topValue = getTop(s, topValue - 1, 0); // открытая скобка, которая еще не закрыта
                }
            }
        }
        return topValue == -1; // true -> все скобки были закрыты и имеют пару
    }

    private int getTop(String s, int index, int right) {
        if (index >= 0){
            char ch = s.charAt(index);
            if (map.containsKey(ch)) { //текущий элемент - закрывающий
                right++;
            } else {
                right--;
            }
            if (right < 0) {
                return index;
            }
            index--;
            return getTop(s, index, right);
        }
        else return -1;
    }

    public static void main(String[] args) {
        String s = "[[(){}{[[]{}{()}]}]]";
        String result = s.replaceAll("[^(){}\\]\\[]+", "");
        Main m = new Main();
        System.out.println(result);
        System.out.print(m.isValid(result));
    }

}