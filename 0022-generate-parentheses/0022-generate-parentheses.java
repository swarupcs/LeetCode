class Solution {
  public List<String> generateParenthesis(int n) {
        List<String> ll = new ArrayList<>();
        Parenthesis(n, 0, 0, "", ll);
        return ll;
    }

    public static void Parenthesis(int n, int open, int close, String ans, List<String> ll) {
		if (open == n && close == n) {
			//System.out.print(ans + " ");
			ll.add(ans);
			return;
		}
		if (open > n || close > open) {
			return;
		}
		Parenthesis(n, open + 1, close, ans + "(", ll);
		Parenthesis(n, open, close + 1, ans + ")", ll);
	}
}