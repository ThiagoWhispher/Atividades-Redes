# iPerf3
Usage: iperf [-s|-c host] [options]
       iperf [-h|--help] [-v|--version]

### Server specific:
  **-s, --server**          run in server mode
### Client specific:
  **-c, --client <host>**   run in client mode, connecting to <host>
  **-P, --parallel** #      number of parallel client streams to run
  **-w, --window** #[KMG]   set window size / socket buffer size
  **-M, --set-mss** #       set TCP/SCTP maximum segment size (MTU - 40 bytes)

[KMG] indicates options that support a K/M/G suffix for kilo-, mega-, or giga-

iperf3 homepage at: http://software.es.net/iperf/
Report bugs to:     https://github.com/esnet/iperf
