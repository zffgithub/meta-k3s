# meta-k3s

This layer is based upon the work of https://github.com/janeczku/meta-k3s. The following changes have been made

* Kernel configuration
* K3S Binary SHA
* Data dir on Mender partition

## Supported target architectures

The recipe makes use of [upstream binaries](https://github.com/rancher/k3s/releases/latest) and are not compiled during the compilation process of this layer.

## Dependencies

```
  URI: git://git.yoctoproject.org/poky.git
  branch: warrior
```

## Installation

```
IMAGE_INSTALL_append = " k3s k3s-init"
```

## Configuration

Set the following values in your `local.conf`

* `K3S_URL`
* `K3S_CLUSTER_SECRET`
* `K3S_VERSION`
* `K3S_SHA_${ARM64,AMD64,ARM}`
