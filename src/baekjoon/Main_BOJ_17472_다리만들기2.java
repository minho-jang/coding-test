package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17472_다리만들기2 {
    static int[] dr = {0, 1, -1, 0};
    static int[] dc = {1, 0, 0, -1};
    //				      우, 하, 상, 좌
    static int N, M;
    static int[][] map, newMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        map = new int[N][M];
        newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 맵 탐색, 섬 개수 및 섬 번호 지정
        int islandNum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (newMap[i][j] == 0 && map[i][j] == 1) {
                    dfs(i, j, ++islandNum);
                }
            }
        }
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(newMap[i]));
//		}
//		System.out.println();

        // 맵을 통해서 섬간 최소거리 찾기 -> 인접행렬 만들기
        int[][] adjMatrix = new int[islandNum][islandNum];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (newMap[i][j] != 0) {
                    // 다리 연결 확인
                    int num = newMap[i][j];
                    int r = i, c = j;
//					System.out.println(num + " " + r + " " + c);
                    for (int d = 0; d < 4; d++) {
                        int length = 0;
                        int nr = r + dr[d], nc = c + dc[d];
                        while (true) {
                            if (nr < 0 || nr >= N || nc < 0 || nc >= M || newMap[nr][nc] == num)
                                break;

                            if (newMap[nr][nc] != 0) {
                                if (length > 1) {
                                    int from = num - 1, to = newMap[nr][nc] - 1;
                                    if (adjMatrix[from][to] != 0) // 이미 어떤 다리가 존재하면 둘 중 짧은 거로 삽입
                                        adjMatrix[from][to] = adjMatrix[to][from] = Math.min(adjMatrix[from][to], length);
                                    else
                                        adjMatrix[from][to] = adjMatrix[to][from] = length;

//									System.out.println("==> " + from + " " + to + " " + adjMatrix[from][to]);
                                }

                                break;
                            }

                            nr += dr[d];
                            nc += dc[d];
                            length++;
                        }
                    }
                }
            }
        }

//		for (int i = 0; i < islandNum; i++) {
//			System.out.println(Arrays.toString(adjMatrix[i]));
//		}

        // 인접행렬을 통해 MST 찾기 (프림 알고리즘)
        final int INF = Integer.MAX_VALUE;
        boolean[] visited = new boolean[islandNum];
        int[] distance = new int[islandNum];
        Arrays.fill(distance, INF);
        distance[0] = 0;    // 0 에서 출발

        int min = 0, cur = 0;
        for (int i = 0; i < islandNum; i++) {
            min = INF;
            cur = -1;
            for (int k = 0; k < islandNum; k++) {
                if (!visited[k] && min > distance[k]) {
                    min = distance[k];
                    cur = k;
                }
            }

            if (cur < 0) break;
            visited[cur] = true;

            for (int j = 0; j < islandNum; j++) {
                if (!visited[j] && adjMatrix[cur][j] > 0 && adjMatrix[cur][j] < distance[j]) {
                    distance[j] = adjMatrix[cur][j];
                }
            }
        }
//		System.out.println(Arrays.toString(distance));

        int sum = 0;
        for (int i = 0; i < islandNum; i++) {
            if (distance[i] == INF) {
                sum = -1;
                break;
            }
            sum += distance[i];
        }

        System.out.println(sum);
    }

    private static void dfs(int r, int c, int islandNum) {
        newMap[r][c] = islandNum;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                continue;

            if (newMap[nr][nc] == 0 && map[nr][nc] == 1) {
                dfs(nr, nc, islandNum);
            }
        }
    }
}

/*

public class Main_BOJ_17472_다리만들기22 {
	static int[] dr = {0, 1, -1, 0};
	static int[] dc = {1, 0, 0, -1};
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 섬 넘버링
		int islandNum = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					
					// 해당 모든 구역 넘버링
					dfs(map, i, j, islandNum++);
					
				}
			}
		}
		
		// 2부터 넘버링 한 걸 1씩 감소
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0)
					map[i][j]--;
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		islandNum -= 2;
		// 현 위치가 섬이라면 사방으로 다리를 건설하고 길이를 구한다.
		// 이미 길이가 존재하면 최소값을 저장한다.
		int[][] adjMatrix = new int[islandNum][islandNum];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				if (map[i][j] > 0) {
					int r=i, c=j;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						int length = 0;	// 다리 길이
						// 한 방향으로 직진. 다리 건설
						while(true) {
							
							if (nr < 0 || nr >= N || nc < 0 || nc >= M) 
								break;
							if (map[nr][nc] == map[i][j])
								break;
							if (map[nr][nc] > 0) {
								if (length < 2) // 길이가 2이상이어야 한다
									break;
								
								// 인접행렬에 저장
								if (adjMatrix[map[i][j]-1][map[nr][nc]-1] == 0)
									adjMatrix[map[i][j]-1][map[nr][nc]-1] = length;
								else
									adjMatrix[map[i][j]-1][map[nr][nc]-1] = Math.min(length, adjMatrix[map[i][j]-1][map[nr][nc]-1]);
								break;
							}
							
							length++;
							nr += dr[d];
							nc += dc[d];
						}
						
					}
					
				}
					
			}
		}
		
//		for (int i = 0; i < islandNum; i++) {
//			System.out.println(Arrays.toString(adjMatrix[i]));
//		}
//		System.out.println();
		
		// 만들어진 인접행렬로 MST 구하기
		// 0에서 출발
		int[] distance = new int[islandNum];
		Arrays.fill(distance, 987654321);
		distance[0] = 0;
		boolean[] visited = new boolean[islandNum];
		for (int i = 0; i < islandNum; i++) {
			// distance 중 최소값 찾기
			int min = 987654321;
			int minIdx = -1;
			for (int k = 0; k < islandNum; k++) {
				if (!visited[k] && min > distance[k]) {
					min = distance[k];
					minIdx = k;
				}
			}
			
			if (minIdx == -1)
				break;
			
			visited[minIdx] = true;
			
			// 거리 업데이트
			for (int k = 0; k < islandNum; k++) {
				if (!visited[k] && adjMatrix[minIdx][k] > 0 && distance[k] > adjMatrix[minIdx][k])
					distance[k] = adjMatrix[minIdx][k];
			}
		}
		
//		System.out.println(Arrays.toString(distance));
		
		int distanceSum = 0;
		for (int i = 0; i < islandNum; i++) {
			if (distance[i] == 987654321) {
				System.out.println(-1);
				return;
			}
			distanceSum += distance[i];
		}
		System.out.println(distanceSum);
	}

	private static void dfs(int[][] map, int r, int c, int num) {
		map[r][c] = num;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) 
				continue;
			
			if (map[nr][nc] == 1)
				dfs(map, nr, nc, num);
		}
	}
}



*/