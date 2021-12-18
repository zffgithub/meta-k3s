FILESEXTRAPATHS:prepend := "${THISDIR}/linux:"

SRC_URI += "file://k3s.cfg \
           "

KERNEL_MODULE_AUTOLOAD += "xt_conntrack"
KERNEL_MODULE_AUTOLOAD += "xt_addrtype"
KERNEL_MODULE_AUTOLOAD += "configs"
