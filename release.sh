git config --global user.email "krfdtv@gmail.com"
git config --global user.name "kotprotiv"

if git log -1 | grep -q "#major"; then
  mvn build-helper:parse-version release:prepare -DreleaseVersion=\${parsedVersion.nextMajorVersion}.0.0 -Dusername=kotprotiv -Dpassword="$TOKEN"
elif git log -1 | grep -q "#minor"; then
  mvn build-helper:parse-version release:prepare -DreleaseVersion=\${parsedVersion.majorVersion}.\${parsedVersion.nextMinorVersion}.0 -Dusername=kotprotiv -Dpassword="$TOKEN"
elif git log -1 | grep -q "#patch"; then
  mvn build-helper:parse-version release:prepare -DreleaseVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion} -Dusername=kotprotiv -Dpassword="$TOKEN"
fi