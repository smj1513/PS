N, num, P = map(int, input().split())

if N == 0:
    print(1)
else:
    nums = list(map(int, input().split()))
    if N == P and nums[-1] >= num:
        print(-1)
    else:
        nums.append(num)
        nums.sort(reverse=True)

        ranks = [0] * len(nums)
        ranks[0] = 1
        for i in range(1, len(nums)):
            if nums[i] == nums[i - 1]:
                ranks[i] = ranks[i - 1]
            else:
                ranks[i] = i + 1

        target_index = nums.index(num)

        if target_index < P:
            print(ranks[target_index])
        else:
            print(-1)