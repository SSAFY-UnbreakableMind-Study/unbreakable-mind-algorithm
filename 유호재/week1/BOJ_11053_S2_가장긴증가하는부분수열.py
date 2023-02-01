
def main():
    n = int(input())
    graph = list(map(int, input().split()))
    count = [0]*n
    for i in range(n):
        max_count = 0
        for j in range(i):
            if graph[j] < graph[i]:
                max_count = max(max_count, count[j])
        count[i] = max_count +1
    print(max(count))


if __name__ == "__main__":
    main()
