SUMMARY = "Custom Raspberry Pi 5 image"
LICENSE = "MIT"

require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL:append = " \
    connman \
    wpa-supplicant \
    iw \
    linux-firmware \
    net-tools \
    jambhul-network \
    jambhul-device-config \
"