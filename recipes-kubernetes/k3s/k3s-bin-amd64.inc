require k3s-bin.inc
SRC_URI = "https://github.com/rancher/k3s/releases/download/v${K3S_VERSION}%2Bk3s1/k3s;unpack=0;name=k3s;downloadfilename=k3s"
SRC_URI[k3s.sha256sum] = "${K3S_SHA_AMD64}"
