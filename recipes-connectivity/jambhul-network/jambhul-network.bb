SUMMARY = "Wi-Fi configuration for jambhul"
LICENSE = "CLOSED"

SRC_URI = "file://jambhul-wifi.config"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${localstatedir}/lib/connman
    install -m 0600 ${WORKDIR}/jambhul-wifi.config \
        ${D}${localstatedir}/lib/connman/jambhul-wifi.config
}

FILES:${PN} = "${localstatedir}/lib/connman/jambhul-wifi.config"