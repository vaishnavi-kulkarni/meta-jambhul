SUMMARY = "Root SSH public-key access for the Jambhul image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://authorized_keys"
S = "${WORKDIR}"

do_install() {
    install -d -m 0700 ${D}/root/.ssh
    install -m 0600 ${WORKDIR}/authorized_keys \
        ${D}/root/.ssh/authorized_keys
    chown -R 0:0 ${D}/root/.ssh
}

FILES:${PN} = "/root/.ssh/authorized_keys"
