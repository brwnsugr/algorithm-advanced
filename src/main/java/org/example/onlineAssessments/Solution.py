class Solution:
    def solve(self, n, m, A, B):
        INF = 10 ** 7
        cards = [(A[i], B[i]) for i in range(n)]
        cards.sort()

        # Initialize variables for tracking limits and ranges
        left_change_lim = 0
        right_change_lim = 0

        left_b_min, left_b_max = INF, 0
        l_range_after_k_flips = [(INF, 0)]  # Track range of flipped values after flipping k cards from the left.

        # Determine the left limit by checking if flipping is beneficial
        for i in range(m):
            if cards[i][0] >= cards[i][1] or left_b_min <= cards[i][0]:
                break
            else:
                left_change_lim += 1
                left_b_min = min(left_b_min, cards[i][1])
                left_b_max = max(left_b_max, cards[i][1])
                l_range_after_k_flips.append((left_b_min, left_b_max))

        right_b_min, right_b_max = INF, 0
        r_range_after_k_flips = [(INF, 0)]  # Track range of flipped values after flipping k cards from the right.

        # Determine the right limit by checking if flipping is beneficial
        for i in range(n-1, n-1-m, -1):
            if cards[i][0] <= cards[i][1] or right_b_max >= cards[i][0]:
                break
            else:
                right_change_lim += 1
                right_b_min = min(right_b_min, cards[i][1])
                right_b_max = max(right_b_max, cards[i][1])
                r_range_after_k_flips.append((right_b_min, right_b_max))

        # Calculate the initial range without any flips
        ans = cards[-1][0] - cards[0][0]

        # Start optimizing the range by considering flips using two pointers.
        l = left_change_lim
        r = 0

        # Optimize range considering flips.
        while l >= 0:
            base_min = l_range_after_k_flips[l][0]
            if l < n:
                base_min = min(base_min, cards[l][0])
            base_max = l_range_after_k_flips[l][1]
            curr_min = min(base_min, r_range_after_k_flips[r][0])
            curr_max = max(base_max, r_range_after_k_flips[r][1])
            if n - r - 1 >= 0:
                curr_max = max(curr_max, cards[n - r - 1][0])

            # Attempt to improve the range by adjusting flips
            for j in range(min(m-l, right_change_lim)-r):
                next_min = min(base_min, r_range_after_k_flips[r+1][0])
                next_max = max(base_max, r_range_after_k_flips[r+1][1])
                if n-r-2 >= 0:
                    next_max = max(next_max, cards[n-r-2][0])
                if next_max - next_min <= curr_max - curr_min:
                    curr_min = next_min
                    curr_max = next_max
                    ans = min(ans, next_max - next_min)
                    r += 1
                else:
                    break
            l -= 1
        return ans

if __name__ == "__main__":
    solution = Solution()
    m = 2
    A = [10, 20, 30, 40]
    B = [35, 25, 15, 5]
    n = len(A)
    print(solution.solve(n, m, A, B))