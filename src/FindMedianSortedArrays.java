public class FindMedianSortedArrays {

	/*
	 * 两个排序的数组A和B分别含有m和n个数，找到两个排序数组的中位数，要求时间复杂度应为O(log (m+n))。 给出数组A =
	 * [1,2,3,4,5,6] B = [2,3,4,5]，中位数3.5 给出数组A = [1,2,3] B = [4,5]，中位数 3
	 * 算法思想：详见https://discuss.leetcode.com/topic/16797/very-concise-o-log-min-m-n-iterative-solution-with-detailed-explanation 
	 * This problem is notoriously  hard to implement due to all the corner cases. Most implementations
	 * consider odd-lengthed and even-lengthed arrays as two different cases and
	 * treat them separately. As a matter of fact, with a little mind twist.
	 * These two cases can be combined as one, leading to a very simple solution
	 * where (almost) no special treatment is needed.
	 * 
	 * First, let's see the concept of 'MEDIAN' in a slightly unconventional
	 * way. That is:
	 * 
	 * "if we cut the sorted array to two halves of EQUAL LENGTHS, then median
	 * is the AVERAGE OF Max(lower_half) and Min(upper_half), i.e. the two
	 * numbers immediately next to the cut". For example, for [2 3 5 7], we make
	 * the cut between 3 and 5:
	 * 
	 * [2 3 / 5 7] then the median = (3+5)/2. Note that I'll use '/' to
	 * represent a cut, and (number / number) to represent a cut made through a
	 * number in this article.
	 * 
	 * for [2 3 4 5 6], we make the cut right through 4 like this:
	 * 
	 * [2 3 (4/4) 5 7]
	 * 
	 * Since we split 4 into two halves, we say now both the lower and upper
	 * subarray contain 4. This notion also leads to the correct answer: (4 + 4)
	 * / 2 = 4;
	 * 
	 * For convenience, let's use L to represent the number immediately left to
	 * the cut, and R the right counterpart. In [2 3 5 7], for instance, we have
	 * L = 3 and R = 5, respectively.
	 * 
	 * We observe the index of L and R have the following relationship with the
	 * length of the array N:
	 * 
	 * N Index of L / R 1 0 / 0 2 0 / 1 3 1 / 1 4 1 / 2 5 2 / 2 6 2 / 3 7 3 / 3
	 * 8 3 / 4 It is not hard to conclude that index of L = (N-1)/2, and R is at
	 * N/2. Thus, the median can be represented as
	 * 
	 * (L + R)/2 = (A[(N-1)/2] + A[N/2])/2 To get ready for the two array
	 * situation, let's add a few imaginary 'positions' (represented as #'s) in
	 * between numbers, and treat numbers as 'positions' as well.
	 * 
	 * [6 9 13 18] -> [# 6 # 9 # 13 # 18 #] (N = 4) position index 0 1 2 3 4 5 6
	 * 7 8 (N_Position = 9)
	 * 
	 * [6 9 11 13 18]-> [# 6 # 9 # 11 # 13 # 18 #] (N = 5) position index 0 1 2
	 * 3 4 5 6 7 8 9 10 (N_Position = 11) As you can see, there are always
	 * exactly 2*N+1 'positions' regardless of length N. Therefore, the middle
	 * cut should always be made on the Nth position (0-based). Since index(L) =
	 * (N-1)/2 and index(R) = N/2 in this situation, we can infer that index(L)
	 * = (CutPosition-1)/2, index(R) = (CutPosition)/2.
	 * 
	 * Now for the two-array case:
	 * 
	 * A1: [# 1 # 2 # 3 # 4 # 5 #] (N1 = 5, N1_positions = 11)
	 * 
	 * A2: [# 1 # 1 # 1 # 1 #] (N2 = 4, N2_positions = 9) Similar to the
	 * one-array problem, we need to find a cut that divides the two arrays each
	 * into two halves such that
	 * 
	 * "any number in the two left halves" <= "any number in the two right
	 * halves". We can also make the following observations：
	 * 
	 * There are 2N1 + 2N2 + 2 position altogether. Therefore, there must be
	 * exactly N1 + N2 positions on each side of the cut, and 2 positions
	 * directly on the cut.
	 * 
	 * Therefore, when we cut at position C2 = K in A2, then the cut position in
	 * A1 must be C1 = N1 + N2 - k. For instance, if C2 = 2, then we must have
	 * C1 = 4 + 5 - C2 = 7.
	 * 
	 * [# 1 # 2 # 3 # (4/4) # 5 #]
	 * 
	 * [# 1 / 1 # 1 # 1 #] When the cuts are made, we'd have two L's and two
	 * R's. They are
	 * 
	 * L1 = A1[(C1-1)/2]; R1 = A1[C1/2]; L2 = A2[(C2-1)/2]; R2 = A2[C2/2]; In
	 * the above example,
	 * 
	 * L1 = A1[(7-1)/2] = A1[3] = 4; R1 = A1[7/2] = A1[3] = 4; L2 = A2[(2-1)/2]
	 * = A2[0] = 1; R2 = A1[2/2] = A1[1] = 1; Now how do we decide if this cut
	 * is the cut we want? Because L1, L2 are the greatest numbers on the left
	 * halves and R1, R2 are the smallest numbers on the right, we only need
	 * 
	 * L1 <= R1 && L1 <= R2 && L2 <= R1 && L2 <= R2 to make sure that any number
	 * in lower halves <= any number in upper halves. As a matter of fact, since
	 * L1 <= R1 and L2 <= R2 are naturally guaranteed because A1 and A2 are
	 * sorted, we only need to make sure:
	 * 
	 * L1 <= R2 and L2 <= R1.
	 * 
	 * Now we can use simple binary search to find out the result.
	 * 
	 * If we have L1 > R1, it means there are too many large numbers on the left
	 * half of A1, then we must move C1 to the left (i.e. move C2 to the right);
	 * If L2 > R1, then there are too many large numbers on the left half of A2,
	 * and we must move C2 to the left. Otherwise, this cut is the right one.
	 * After we find the cut, the medium can be computed as (max(L1, L2) +
	 * min(R1, R2)) / 2; Two side notes:
	 * 
	 * A. Since C1 and C2 can be mutually determined from each other, we can
	 * just move one of them first, then calculate the other accordingly.
	 * However, it is much more practical to move C2 (the one on the shorter
	 * array) first. The reason is that on the shorter array, all positions are
	 * possible cut locations for median, but on the longer array, the positions
	 * that are too far left or right are simply impossible for a legitimate
	 * cut. For instance, [1], [2 3 4 5 6 7 8]. Clearly the cut between 2 and 3
	 * is impossible, because the shorter array does not have that many elements
	 * to balance out the [3 4 5 6 7 8] part if you make the cut this way.
	 * Therefore, for the longer array to be used as the basis for the first
	 * cut, a range check must be performed. It would be just easier to do it on
	 * the shorter array, which requires no checks whatsoever. Also, moving only
	 * on the shorter array gives a run-time complexity of O(log(min(N1, N2)))
	 * (edited as suggested by @baselRus)
	 * 
	 * B. The only edge case is when a cut falls on the 0th(first) or the
	 * 2Nth(last) position. For instance, if C2 = 2N2, then R2 = A2[2*N2/2] =
	 * A2[N2], which exceeds the boundary of the array. To solve this problem,
	 * we can imagine that both A1 and A2 actually have two extra elements,
	 * INT_MAX at A[-1] and INT_MAX at A[N]. These additions don't change the
	 * result, but make the implementation easier: If any L falls out of the
	 * left boundary of the array, then L = INT_MIN, and if any R falls out of
	 * the right boundary, then R = INT_MAX.
	 * 我们通过切一刀，能够把有序数组分成左右两个部分，切的那一刀就被称为割(Cut)，割的左右会有两个元素，分别是左边最大值和右边最小值。 我们定义L
	 * = Max(LeftPart)，R = Min(RightPart) Ps.
	 * 割可以割在两个数中间，也可以割在1个数上，如果割在一个数上，那么这个数即属于左边，也属于右边。（后面讲单数组中值问题的时候会说） 比如说[2 3
	 * 5 7]这个序列，割就在3和5之间 [2 3 / 5 7] 中值就是（3+5）/2 = 4 如果[2 3 4 5
	 * 6]这个序列，割在4上，我们可以把4分成2个 [2 3 (4/4) 5 7] 中值就是（4+4）/2 = 4
	 * 这样可以保证不管中值是1个数还是2个数都能统一运算。 割和第k个元素
	 * 
	 * 对于单数组，找其中的第k个元素特别好做，我们用割的思想就是：
	 * 常识1：如果在k的位置割一下，然后A[k]就是L。换言之，就是如果左侧有k个元素，A[
	 * k]属于左边部分的最大值。（都是明显的事情，这个不用解释吧！） 双数组
	 * 
	 * 我们设: C_iC ​i ​​ 为第i个数组的割。 L_iL ​i ​​ 为第i个数组割后的左元素. R_iR ​i ​​
	 * 为第i个数组割后的右元素。 Alt text 我们看如何从双数组里取出第k个元素
	 * 
	 * 首先L_i <= R_iL ​i ​​ <=R ​i ​​ 是肯定的（因为数组有序，左边肯定小于右边） 如果我们让L_1 <= R_2L ​1
	 * ​​ <=R ​2 ​​ && L_2 <= R_1L ​2 ​​ <=R ​1 ​​ Alt text 那么左半边
	 * 全小于右半边，如果左边的元素个数相加刚好等于k，那么第k个元素就是Max(L1,L2)，参考上面常识1。 如果
	 * L1>R2，说明数组1的左边元素太大（多），我们把C1减小，把C2增大。L2>R1同理，把C1增大，C2减小。 假设k=3
	 * 
	 * 对于
	 * 
	 * [1\ 4\ 7\ 9][1 4 7 9] [2\ 3\ 5][2 3 5] 设C1 = 2，那么C2 = k-C1 = 1 [1\ 4/ 7\
	 * 9][1 4/7 9] [2/3\ 5][2/3 5] 这时候，L1(4)>R2(3)，说明C1要减小，C2要增大，C1 = 1，C2=k-C1
	 * = 2 [1/4\ 7\ 9][1/4 7 9] [2\ 3/5][2 3/5] 这时候，满足了L_1 <= R_2L ​1 ​​ <=R ​2
	 * ​​ && L_2 <= R_1L ​2 ​​ <=R ​1 ​​ ，第3个元素就是Max(1,3) = 3。
	 * 如果对于上面的例子，把k改成4就恰好是中值。 下面具体来看特殊情况的中值问题。 双数组的奇偶
	 * 
	 * 中值的关键在于，如何处理奇偶性，单数组的情况，我们已经讨论过了，那双数组的奇偶问题怎么办，m+n为奇偶处理方案都不同， 让数组恒为奇数
	 * 
	 * 有没有办法让两个数组长度相加一定为奇数或偶数呢？
	 * 其实有的，虚拟加入‘#'(这个trick在manacher算法中也有应用)，让数组长度恒为奇数（2n+1恒为奇数）。
	 * Ps.注意是虚拟加，其实根本没这一步，因为通过下面的转换，我们可以保证虚拟加后每个元素跟原来的元素一一对应 Alt text
	 * 
	 * 映射关系
	 * 
	 * 这有什么好处呢，为什么这么加?因为这么加完之后，每个位置可以通过/2得到原来元素的位置。 Alt text
	 * 
	 * 在虚拟数组里表示“割”
	 * 
	 * 不仅如此，割更容易，如果割在‘#'上等于割在2个元素之间，割在数字上等于把数字划到2个部分。 奇妙的是不管哪种情况： Li = (Ci-1)/2
	 * Ri = Ci/2 例：
	 * 
	 * 割在4/7之间‘#'，C = 4，L=(4-1)/2=1 ，R=4/2=2 刚好是4和7的原来位置！ 割在3上，C =
	 * 3，L=(3-1)/2=1，R=3/2 =1，刚好都是3的位置！
	 * 剩下的事情就好办了，把2个数组看做一个虚拟的数组A，目前有2m+2n+2个元素，割在m
	 * +n+1处，所以我们只需找到m+n+1位置的元素和m+n+2位置的元素就行了。(在数组中是[m+n]和[m+n+1]) 左边：A[m+n] =
	 * Max(L1+L2) 右边：A[m+n+1] = Min(R1+R2) Mid = (A[m+n]+A[m+n+1])/2 =
	 * (Max(L1+L2) + Min(R1+R2) )/2 至于在两个数组里找割的方案，就是上面的方案。 分治的思路
	 * 
	 * 有了上面的知识后，现在的问题就是如何利用分治的思想。 怎么分？
	 * 
	 * 最快的分的方案是二分，有2个数组，我们对哪个做二分呢？
	 * 根据之前的分析，我们知道了，只要C1或C2确定，另外一个也就确定了。这里，为了效率，我们肯定是选长度较短的做二分，假设为C1。 怎么治？
	 * 
	 * 也比较简单，我们之前分析了：就是比较L1,L2和R1,R2。 L1>R2，把C1减小，C2增大。—> C1向左二分
	 * L2>R1，把C1增大，C2减小。—> C1向右二分 越界问题
	 * 
	 * 如果C1或C2已经到头了怎么办？ 这种情况出现在：如果有个数组完全小于或大于中值。可能有4种情况： C1 = 0 —— 数组1整体都比中值大，L1
	 * >R2，中值在2中 C2 = 0 —— 数组1整体都比中值小，L1 <R2，中值在1中 C1 = n*2 —— 数组1整体都比中值小，L1
	 * <R2，中位数在2中 C2 = m*2 —— 数组1整体都比中值大，L1 >R2，中位数在1中
	 * 其实，如果我已经确定了数组1是最短的数组，那只有两种情况了，比较好处理： 如果C1 = 0 —> 那么我们缩小L1，L1 =
	 * INT_MIN，保证判断正确。 如果C1 = n*2 —> 那么我们增大R1，R1 = INT_MAX，保证判断正确。
	 * 
	 * @param A: An integer array
	 * 
	 * @param B: An integer array
	 * 
	 * @return: a double whose format is *.5 or *.0
	 */
	public double findMedianSortedArrays(int[] A, int[] B) {
		// write your code here

		if (A.length > B.length) {
			return findMedianSortedArrays(B, A); // 默认A数组长度小于B,否者交换两者的位置
		}
		int N1 = A.length;
		int N2 = B.length;
		int low = 0, high = N1 * 2;
		while (low <= high) {
			int mid1 = (low + high) / 2;
			int mid2 = N1 + N2 - mid1;

			// 边界条件
			double L1 = (mid1 == 0) ? Integer.MIN_VALUE : A[(mid1 - 1) / 2];
			double L2 = (mid2 == 0) ? Integer.MIN_VALUE : B[(mid2 - 1) / 2];
			double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : A[mid1 / 2];
			double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : B[mid2 / 2];

			if (L2 > R1) {
				low = mid1;
			} else if (L1 > R2) {
				high = mid1;
			} else {
				return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
			}
		}

		return -1;
	}

	public double findMedianSortedArrays1(int[] A, int[] B) {
		// write your code here

		int len = A.length + B.length;
		if(len % 2 == 1) { //数组之和为奇数
			return findKthNumber(A, 0, B, 0, len / 2 + 1);
		}else {
			return (findKthNumber(A, 0, B, 0, len / 2 + 1) + findKthNumber(A, 0, B, 0, len / 2)) / 2;
		}
	}
	
	
	/**
	 * 算法思想： 找到数组A和数组B联合起来的第k个数(k从1开始)
	 * 寻找第k小数的问题（假设两个原序列升序排列），这样中位数实际上是第(m+n)/2小的数。所以只要解决了第k小数的问题，原问题也得以解决。
	 * 首先假设数组A和B的元素个数都大于k/2，我们比较A[k/2-1]和B[k/2-1]两个元素，这两个元素分别表示A的第k/2小的元素和B的第k/2小的元素。这两个元素比较共有三种情况：>、<和=。
	 * 如果A[k/2-1]<B[k/2-1]，这表示A[0]到A[k/2-1]的元素都在A和B合并之后的前k小的元素中。换句话说，A[k/2-1]不可能大于两数组合并之后的第k小值，所以我们可以将其抛弃。
	 * 证明也很简单，可以采用反证法。假设A[k/2-1]大于合并之后的第k小值，我们不妨假定其为第（k+1）小值。由于A[k/2-1]小于B[k/2-1]，所以B[k/2-1]至少是第（k+2）小值。
	 * 但实际上，在A中至多存在k/2-1个元素小于A[k/2-1]，B中也至多存在k/2-1个元素小于A[k/2-1]，所以小于A[k/2-1]的元素个数至多有k/2+ k/2-2，小于k，这与A[k/2-1]是第（k+1）的数矛盾。
	 * 当A[k/2-1]>B[k/2-1]时存在类似的结论。当A[k/2-1]=B[k/2-1]时，我们已经找到了第k小的数，也即这个相等的元素，我们将其记为m。
	 * 由于在A和B中分别有k/2-1个元素小于m，所以m即是第k小的数。(这里可能有人会有疑问，如果k为奇数，则m不是中位数。这里是进行了理想化考虑，在实际代码中略有不同，是先求k/2，然后利用k-k/2获得另一个数。)
	 * 通过上面的分析，我们即可以采用递归的方式实现寻找第k小的数。此外我们还需要考虑几个边界条件：
	 * 如果A或者B为空，则直接返回B[k-1]或者A[k-1]；
	 * 如果k为1，我们只需要返回A[0]和B[0]中的较小值；
	 * 如果A[k/2-1]=B[k/2-1]，返回其中一个；
	 * @param A  数组A
	 * @param aStart 数组A的偏移量
	 * @param B  数组B
	 * @param bStart 数组B的偏移量
	 * @param k  数组A和数组B联合起来的第k个数
	 * @return
	 */
	public static int findKthNumber(int[] A, int aStart, int[] B, int bStart, int k) {
		//边界条件
		if(aStart >= A.length) { //如果aStart >= A.length,证明第k个数在数组B中，B[bStart + k - 1]即为第k个数
			return B[bStart + k - 1];
		}
		if(bStart >= B.length) {//如果bStart >= B.length,证明第k个数在数组A中，A[aStart + k - 1]即为第k个数
			return A[aStart + k - 1];
		}
		if(k == 1) {
			return Math.min(A[aStart], B[bStart]);
		}
		
		int aKey = aStart + k / 2 - 1 < A.length ? A[aStart + k / 2 - 1] : Integer.MAX_VALUE;
		int bKey = bStart + k / 2 - 1 < B.length ? B[bStart + k / 2 - 1] : Integer.MAX_VALUE;
		if (aKey < bKey) {
			return findKthNumber(A, aStart + k / 2, B, bStart, k - k / 2);
		}else {
			return findKthNumber(A, aStart, B, bStart + k / 2, k - k / 2);
		}
	}

}
