package array;

public class SortVowels {
    public String sortVowels(String s) {
        int n = s.length();
        int[] freq = new int[40];
        char[] arr = s.toCharArray();
        int maxIdx = -1;
        for (char c : arr) {
            if (isVowel(c)) {
                freq[c - 'A']++;
                maxIdx = Math.max(maxIdx, c - 'A');
            }
        }
        int j = 0;
        for (int i = 0; i <= maxIdx; i++) {
            char c = (char)('A' + i);
            while(freq[i] != 0) {
                freq[i]--;
                while (!isVowel(arr[j]))
                    j++;
                arr[j++] = c;
            }
        }
        return maxIdx == -1 ? s : new String(arr);
    }

    boolean isVowel(char c) {
        switch(c) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        final SortVowels sortVowels = new SortVowels();
        sortVowels.sortVowels("lEetcOde");
    }
}
