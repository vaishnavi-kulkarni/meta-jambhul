SUMMARY = "Jambhul device configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://79-eth0-static.network \
    file://main.conf \
    file://getty-autologin.conf \
    file://serial-getty-autologin.conf \
    file://10-ssh-security.conf \
"

S = "${WORKDIR}"

inherit systemd

RDEPENDS:${PN} += "connman systemd"

do_install() {
    install -d ${D}${sysconfdir}/connman
    install -m 0644 ${WORKDIR}/main.conf \
        ${D}${sysconfdir}/connman/main.conf

    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/79-eth0-static.network \
        ${D}${sysconfdir}/systemd/network/79-eth0-static.network

    install -d ${D}${sysconfdir}/systemd/system/getty@.service.d
    install -m 0644 ${WORKDIR}/getty-autologin.conf \
        ${D}${sysconfdir}/systemd/system/getty@.service.d/autologin.conf

    install -d ${D}${sysconfdir}/systemd/system/serial-getty@.service.d
    install -m 0644 ${WORKDIR}/serial-getty-autologin.conf \
        ${D}${sysconfdir}/systemd/system/serial-getty@.service.d/autologin.conf

    install -d ${D}${sysconfdir}/ssh/sshd_config.d
    install -m 0644 ${WORKDIR}/10-ssh-security.conf \
        ${D}${sysconfdir}/ssh/sshd_config.d/10-ssh-security.conf
}

FILES:${PN} = " \
    ${sysconfdir}/connman/main.conf \
    ${sysconfdir}/systemd/network/79-eth0-static.network \
    ${sysconfdir}/systemd/system/getty@.service.d/autologin.conf \
    ${sysconfdir}/systemd/system/serial-getty@.service.d/autologin.conf \
    ${sysconfdir}/ssh/sshd_config.d/10-ssh-security.conf \
"