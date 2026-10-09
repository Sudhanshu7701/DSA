
class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int balance = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                balance += 2;
            } else {
                balance--;
            }

            if (balance < 0) {
                insertions++;
                balance = 1;
            }

            if (ch == '(' && balance % 2 == 1) {
                insertions++;
                balance--;
            }
        }

        return insertions + balance;
    }
}