class Solution:
  def __init__(self):
    self.solve_count = 0

  def solveNQueens(self, n: int) -> list[list[str]]:
    # Check if queen at row and columns is safe from other queens
    def issafe(r,c):
      n = len(board)
      for i in range(n):
        # Check Queen present at upper direction (same column)
        if board[i][c] == 'Q':
          return False
        # Check Queen is present at left upper diagonal direction
        if r - i >= 0 and c - i >= 0 and board[r-i][c-i] == 'Q':
          return False
        # Check Queen is present at right upper diagonal direction
        if r - i >= 0 and c + i < n and board[r-i][c+i] == 'Q':
          return False
      return True

    # Recursive method
    # Starts from row 0 and iterates all the columns
    #
    def solve(r):
      n = len(board)
      self.solve_count += 1
      # If row is at max, then add the solution to ANS and print current board
      if r == n:
        print(board)
        ans.append(["".join(i) for i in board])
        return

      # Iterate column by colum
      for c in range(0,n):
        # print(r,c)
        # If queen is not safe, then move back to previous row and change queen's column. Then check for safety
        if issafe(r,c):
          # If Queen is safe, then place the queen. Now, move to next row
          board[r][c] = 'Q'
          solve(r+1)
          # Remove the queen
          board[r][c] = '.'

    # Create Empty board
    board = [['.']*n for i in range(n)]
    # Collect all possible answerws
    ans =[]
    # start with 0th row
    solve(0)
    # At the very end, the board will be clear
    print(board)

    print(self.solve_count)
    return ans

s = Solution()
s.solveNQueens(8)
