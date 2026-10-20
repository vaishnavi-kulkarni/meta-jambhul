SUMMARY = "Jambhul device configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://jambhul-eth0-static.service \
    file://main.conf \
    file://getty-autologin.conf \
    file://serial-getty-autologin.conf \
    file://10-ssh-security.conf \
"

S = "${WORKDIR}"

inherit systemd

RDEPENDS:${PN} += "connman iproute2 systemd"

do_install() {
    install -d ${D}${sysconfdir}/connman
    install -m 0644 ${WORKDIR}/main.conf \
        ${D}${sysconfdir}/connman/main.conf

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/jambhul-eth0-static.service \
        ${D}${systemd_system_unitdir}/jambhul-eth0-static.service

    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
    ln -s ${systemd_system_unitdir}/jambhul-eth0-static.service \
        ${D}${sysconfdir}/systemd/system/multi-user.target.wants/jambhul-eth0-static.service

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
    ${systemd_system_unitdir}/jambhul-eth0-static.service \
    ${sysconfdir}/systemd/system/multi-user.target.wants/jambhul-eth0-static.service \
    ${sysconfdir}/systemd/system/getty@.service.d/autologin.conf \
    ${sysconfdir}/systemd/system/serial-getty@.service.d/autologin.conf \
    ${sysconfdir}/ssh/sshd_config.d/10-ssh-security.conf \
"