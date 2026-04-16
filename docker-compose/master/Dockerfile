FROM postgres:16

# Locale paketlerini yükle
RUN apt-get update && apt-get install -y locales \
    && sed -i '/tr_TR.UTF-8/s/^# //g' /etc/locale.gen \
    && locale-gen

ENV LANG=tr_TR.UTF-8
ENV LANGUAGE=tr_TR:tr
ENV LC_ALL=tr_TR.UTF-8

# Init script
COPY init-master.sh /docker-entrypoint-initdb.d/

RUN chmod +x /docker-entrypoint-initdb.d/init-master.sh