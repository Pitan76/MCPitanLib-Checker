<?php
$gradle_properties = file_get_contents('../gradle.properties');
preg_match('/mod_version=(.*)/', $gradle_properties, $matches);
$matches[1] = str_replace(["\r", "\n"], '', $matches[1]);

define('VERSION', $matches[1]);

define('GROUP_ID', 'net.pitan76');
define('ARTIFACT_ID', 'mcpitanlib_checker');

define('DIRS', array(
	'common' => 'common/build/',
	'fabric' => 'fabric/build/',
	'forge' => 'forge/build/',
));

define('PLATFORM_FILE_MARK', array(
	'common' => '',
	'fabric' => '-fabric',
	'forge' => '-forge',
));

foreach (DIRS as $type => $dir) {
	$postData = array();
	
	$postData['group_id'] = GROUP_ID;
	$postData['artifact_id'] = ARTIFACT_ID . '-' . $type;
	$postData['version'] = VERSION;
	
	$files = array(
		$dir . 'libs/' . ARTIFACT_ID . PLATFORM_FILE_MARK[$type] . '-' . VERSION . '.jar',
		$dir . 'libs/' . ARTIFACT_ID . PLATFORM_FILE_MARK[$type] . '-' . VERSION . '-sources.jar',
		$dir . 'publications/maven' .  ucfirst($type) . '/' . ARTIFACT_ID . '.pom',
	);
	
	$pom = '../' . $dir . 'publications/maven' .  ucfirst($type) . '/' . ARTIFACT_ID . '.pom';
	
	$pom_str = file_get_contents($pom);
	$pom_str = preg_replace('/' . preg_quote(VERSION . PLATFORM_FILE_MARK[$type] . '</version>', '/'). '/', VERSION . '</version>', $pom_str, 1);
	$pom_str = preg_replace('/' . preg_quote(ARTIFACT_ID . PLATFORM_FILE_MARK[$type]  . '</artifactId>', '/') . '/', ARTIFACT_ID .  ($type == "common" ? '-common' : PLATFORM_FILE_MARK[$type]) . '</artifactId>', $pom_str, 1);
	
	file_put_contents($pom, $pom_str);
	
	foreach ($files as $index => $file) {
		$postData['upload[' . $index . ']'] = curl_file_create(
			realpath("../" . $file),
			mime_content_type("../" . $file),
			basename("../" . $file)
		);
		echo "Uploading '" . $file . "'\n";
	}
	
	$request = curl_init('http://localhost/maven/maven.php');
	curl_setopt($request, CURLOPT_POST, true);
	curl_setopt($request, CURLOPT_POSTFIELDS, $postData);
	curl_setopt($request, CURLOPT_RETURNTRANSFER, true);
	$result = curl_exec($request);
	
	if ($result === false) {
		error_log(curl_error($request));
	}
	curl_close($request);
}
