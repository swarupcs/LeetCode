class Solution {
    public void dfs(int i, int j,boolean[][]visited,char[][]board){
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || board[i][j]!='O'
        || visited[i][j]
        ){
            return;
        }
        //basically marking it as danger
        visited[i][j]=true;
        dfs(i+1,j,visited,board);
        dfs(i-1,j,visited,board);
        dfs(i,j+1,visited,board);
        dfs(i,j-1,visited,board);

        
    }
    public void solve(char[][] board) {
        int n=board.length;
        int m=board[0].length;
        boolean[][]visited=new boolean[n][m];
        for(int i=0;i<n;i++){
            dfs(i,0,visited,board);
            dfs(i,m-1,visited,board);
        }
        for(int j=0;j<m;j++){
            dfs(0,j,visited,board);
            dfs(n-1,j,visited,board);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                char c=board[i][j];
                if(c=='O' && !visited[i][j]){
                    board[i][j]='X';
                }
            }
        }
        
    }
}