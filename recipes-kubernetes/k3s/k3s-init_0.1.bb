SUMMARY = "Sysvinit script and systemd unit for k3s"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "file://k3s.init \
           file://k3s.service \
           file://k3s.default"

S = "${WORKDIR}"

inherit update-rc.d systemd features_check

RDEPENDS:${PN} = "k3s"

K3S_URL ?= "defaultk3surl"
K3S_CLUSTER_SECRET ?= "defaultk3ssecret"

do_install() {
	# Startup configuration
	install -Dm0644 ${S}/k3s.default ${D}${sysconfdir}/default/k3s
	# set K3S access
	sed -i -e 's,@K3S_URL@,${K3S_URL},g' \
		-e 's,@K3S_CLUSTER_SECRET@,${K3S_CLUSTER_SECRET},g' \
		${D}${sysconfdir}/default/k3s

	# Systemd
	install -Dm0644 ${S}/k3s.service ${D}${systemd_unitdir}/system/k3s.service

	# Sysvinit
	install -Dm755 ${S}/k3s.init ${D}/${sysconfdir}/init.d/k3s

	# Expand paths
	sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
		-e 's,@BINDIR@,${bindir},g' \
		-e 's,@SBINDIR@,${sbindir},g' \
		${D}${systemd_unitdir}/system/k3s.service ${D}/${sysconfdir}/init.d/k3s
}

FILES:${PN} += " \
    ${sysconfdir}/default/k3s \
    ${systemd_unitdir}/system/k3s.service \
    ${sysconfdir}/init.d/k3s"

INITSCRIPT_NAME = "k3s"
INITSCRIPT_PARAMS = "defaults 95 20"

SYSTEMD_SERVICE:${PN} = "k3s.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
