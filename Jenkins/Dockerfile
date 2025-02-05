# 멀티 플랫폼을 지원하는 공식 Jenkins 이미지 사용
FROM jenkins/jenkins:lts-jdk17

# root 권한으로 전환
USER root

# 시스템 패키지 업데이트 및 필요한 패키지 설치
RUN apt-get update && \
    apt-get install -y \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg \
    lsb-release \
    sudo && \
    rm -rf /var/lib/apt/lists/*

# Docker 리포지토리 설정 및 Docker CLI 설치
RUN mkdir -p /etc/apt/keyrings && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /etc/apt/keyrings/docker.gpg && \
    echo \
    "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian \
    $(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null && \
    apt-get update && \
    apt-get install -y docker-ce-cli && \
    rm -rf /var/lib/apt/lists/* 

# 아키텍처별 Docker Compose 설치
RUN ARCH=$(dpkg --print-architecture) && \
    case ${ARCH} in \
        "arm64") COMPOSE_ARCH="aarch64" ;; \
        "amd64") COMPOSE_ARCH="x86_64" ;; \
        *) echo "Unsupported architecture: ${ARCH}" && exit 1 ;; \
    esac && \
    curl -L "https://github.com/docker/compose/releases/download/v2.24.5/docker-compose-Linux-${COMPOSE_ARCH}" -o /usr/local/bin/docker-compose && \
    chmod +x /usr/local/bin/docker-compose

# docker 그룹 생성 및 jenkins 사용자 추가
RUN groupadd -f docker && usermod -aG docker jenkins 

# Jenkins 홈 데이터 복사
COPY jenkins_home_backup/ /var/jenkins_home/

# 파일 권한 수정
RUN chown -R jenkins:jenkins /var/jenkins_home

USER jenkins
